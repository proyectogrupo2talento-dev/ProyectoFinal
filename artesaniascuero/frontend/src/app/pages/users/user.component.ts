import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';

import { User } from '../../models/user.model';
import { UserService } from '../../core/services/user.service';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users: User[] = [];
  loading: boolean = false;
  mensajeError: string = '';
  mensajeExito: string = '';
  mostrarModal: boolean = false;
  editando: boolean = false;
  userEditandoId: number | null = null;

  nuevoUsuario: any = {
    username: '',
    email: '',
    password: '',
    role: 'USER',
    enabled: true
  };

  constructor(
    private userService: UserService,
    private location: Location,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios(): void {
    this.loading = true;
    this.cdr.markForCheck();

    this.userService.getUsers().subscribe({
      next: (data: User[]) => {
        this.users = data;
        this.loading = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        this.mensajeError = err?.error?.message || 'Error al cargar usuarios';
        this.loading = false;
        this.cdr.markForCheck();
      }
    });
  }

  abrirModal(): void {
    this.mensajeError = '';
    this.editando = false;
    this.userEditandoId = null;
    this.nuevoUsuario = {
      username: '',
      email: '',
      password: '',
      role: 'USER',
      enabled: true
    };
    this.mostrarModal = true;
  }

  cerrarModal(): void {
    this.mensajeError = '';
    this.mostrarModal = false;
  }

  guardarUsuario(form: NgForm): void {
    this.mensajeError = '';
    this.mensajeExito = '';

    if (form.invalid) {
      this.mensajeError = 'Completa todos los campos obligatorios';
      return;
    }

    const payload = {
      username: this.nuevoUsuario.username,
      email: this.nuevoUsuario.email,
      password: this.nuevoUsuario.password,
      role: this.nuevoUsuario.role,
      enabled: this.nuevoUsuario.enabled
    };

    const request$ = this.editando && this.userEditandoId !== null
      ? this.userService.updateUser(this.userEditandoId, payload)
      : this.userService.createUser(payload);

    request$.subscribe({
      next: () => {
        this.cargarUsuarios();
        this.mensajeExito = this.editando ? 'Usuario actualizado correctamente' : 'Usuario creado correctamente';
        this.cerrarModal();
        this.cdr.markForCheck();
      },
      error: (err) => {
        this.mensajeError = err?.error?.message || 'No se pudo guardar el usuario';
        this.cdr.markForCheck();
      }
    });
  }

  editarUsuario(user: User): void {
    if (user.id == null) {
      this.mensajeError = 'No se puede editar un usuario sin id';
      return;
    }

    this.mensajeError = '';
    this.editando = true;
    this.userEditandoId = user.id;
    this.nuevoUsuario = {
      username: user.username,
      email: user.email,
      password: user.password,
      role: user.role,
      enabled: user.enabled
    };
    this.mostrarModal = true;
  }

  eliminarUsuario(user: User): void {
    if (user.id == null) {
      this.mensajeError = 'No se puede eliminar un usuario sin id';
      return;
    }

    const confirmado = confirm(`¿Eliminar el usuario ${user.username}?`);
    if (!confirmado) {
      return;
    }

    this.userService.deleteUser(user.id).subscribe({
      next: () => {
        this.mensajeExito = 'Usuario eliminado correctamente';
        this.cargarUsuarios();
        this.cdr.markForCheck();
      },
      error: (err) => {
        this.mensajeError = err?.error?.message || 'No se pudo eliminar el usuario';
        this.cdr.markForCheck();
      }
    });
  }

  volver(): void {
    this.location.back();
  }
}
