import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { isPlatformBrowser } from '@angular/common';

interface LoginResponse {
    token: string;
    role: string;
    username: string;
}

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private apiUrl = 'http://localhost:8080/api/auth';
    private isBrowser: boolean;

    constructor(
        private http: HttpClient,
        @Inject(PLATFORM_ID) private platformId: Object
    ) {
        this.isBrowser = isPlatformBrowser(this.platformId);
    }

    login(username: string, password: string): Observable<void> {

        return this.http.post<LoginResponse>(`${this.apiUrl}/login`, { username, password }).pipe(

            map(response => {

                if (this.isBrowser) {
                    localStorage.setItem('authToken', response.token);
                    localStorage.setItem('role', response.role);
                    localStorage.setItem('username', response.username);
                }

                return;

            })

        );
    }

    logout(): void {

        if (this.isBrowser) {
            localStorage.removeItem('authToken');
            localStorage.removeItem('role');
            localStorage.removeItem('username');
        }

    }

    isAuthenticated(): boolean {

        if (!this.isBrowser) return false;

        return !!localStorage.getItem('authToken');

    }

    getRole(): string {

        if (!this.isBrowser) return '';

        return localStorage.getItem('role') || '';

    }

}