import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MaterialLot } from '../models/material-lot';

@Injectable({
  providedIn: 'root'
})
export class MaterialLotService {
  
  private apiUrl = 'http://localhost:8080/api/material-lots';

  constructor(private http: HttpClient) { }

  getLots(): Observable<MaterialLot[]> {
    return this.http.get<MaterialLot[]>(this.apiUrl);
  }

  getLotById(id: number): Observable<MaterialLot> {
    return this.http.get<MaterialLot>(`${this.apiUrl}/${id}`);
  }

  createLot(lot: MaterialLot): Observable<MaterialLot> {
    return this.http.post<MaterialLot>(this.apiUrl, lot);
  }
}