import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { catchError, throwError, timeout, TimeoutError } from 'rxjs';

const REQUEST_TIMEOUT_MS = 10000;

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const platformId = inject(PLATFORM_ID);

  const applyTimeout = (request$: ReturnType<typeof next>) => request$.pipe(
    timeout(REQUEST_TIMEOUT_MS),
    catchError((error) => {
      if (error instanceof TimeoutError) {
        return throwError(() => new HttpErrorResponse({
          status: 408,
          statusText: 'Request Timeout',
          url: req.url,
          error: {
            message: 'La solicitud excedio el tiempo de espera. Intenta nuevamente.'
          }
        }));
      }

      return throwError(() => error);
    })
  );

  if (!isPlatformBrowser(platformId)) {
    return applyTimeout(next(req));
  }

  const token = localStorage.getItem('authToken');

  if (!token || req.url.includes('/api/auth/login')) {
    return applyTimeout(next(req));
  }

  const authReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ${token}`
    }
  });

  return applyTimeout(next(authReq));
};
