import { ChangeDetectorRef, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username = '';
  password = '';
  loading = false;
  mensajeError = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) { }

  login(): void {

    this.mensajeError = '';
    this.loading = true;
    this.cdr.markForCheck();

    this.authService.login(this.username, this.password)
      .subscribe({
        next: () => {
          this.router.navigate(['/home']);
        },
        error: (err) => {
          if (err?.status === 401) {
            this.mensajeError = 'Credenciales incorrectas';
          } else {
            this.mensajeError = err?.error?.message || 'No fue posible iniciar sesion';
          }
          this.loading = false;
          this.cdr.markForCheck();
        }
      });

  }

}