import { ApplicationConfig, provideZonelessChangeDetection } from '@angular/core';  // ← Cambiar import
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZonelessChangeDetection(),  // ← Cambiar esta línea
    provideHttpClient()
  ]
};