import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';  // ← AGREGAR ESTE IMPORT
import { Material } from '../../models/material';
import { MaterialService } from '../../services/material.service';

@Component({
  selector: 'app-material-list',
  standalone: true,
  imports: [CommonModule, FormsModule],  // ← AGREGAR FormsModule AQUÍ
  templateUrl: './material-list.html',
  styleUrl: './material-list.css'
})
export class MaterialListComponent implements OnInit {
  materiales: Material[] = [];
  loading = false;
  
  // ← AGREGAR ESTO:
  filtroCategoria: string = '';  // Categoría seleccionada en el dropdown

  // ← AGREGAR ESTO: Getter para filtrar materiales
  get materialesFiltrados(): Material[] {
    if (!this.filtroCategoria) {
      return this.materiales;  // Si no hay filtro, devolver todos
    }
    return this.materiales.filter(m => m.category === this.filtroCategoria);
  }

  constructor(private materialService: MaterialService) { }

  ngOnInit(): void {
    this.cargarMateriales();
  }

  cargarMateriales(): void {
    this.loading = true;
    this.materialService.getMaterials().subscribe({
      next: (data) => {
        this.materiales = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error al cargar materiales:', err);
        this.loading = false;
      }
    });
  }
}