import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Material } from '../models/material';

@Injectable({
  providedIn: 'root'
})
export class MaterialService {
  
  private apiUrl = 'http://localhost:8080/api/materials';

  constructor(private http: HttpClient) { }

  getMaterials(): Observable<Material[]> {
    // TEMPORAL: Datos mockeados para pruebas sin backend
    return of([
      { id: 1, name: 'Cuero vacuno', category: 'Materia prima', baseUnit: 'm2', stockMin: 5.00, active: true },
      { id: 2, name: 'Hilo encerado', category: 'Insumos', baseUnit: 'metros', stockMin: 100.00, active: true },
      { id: 3, name: 'Hebilla metal', category: 'Accesorios', baseUnit: 'unidad', stockMin: 20.00, active: true },
      { id: 4, name: 'Forro tela', category: 'Insumos', baseUnit: 'm2', stockMin: 10.00, active: true }
    ] as Material[]);
    
    // REAL (para después, cuando el backend Java esté corriendo):
    // return this.http.get<Material[]>(this.apiUrl);
  }
}