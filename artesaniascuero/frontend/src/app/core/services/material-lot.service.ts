import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MaterialLot } from '../../models/material-lot.model';

@Injectable({
    providedIn: 'root'
})

export class MaterialLotService {

    private apiUrl = 'http://localhost:8080/api/material-lots';

    constructor(private http: HttpClient) { }

    getMaterialLots(): Observable<MaterialLot[]> {
        return this.http.get<MaterialLot[]>(this.apiUrl);
    }

    createMaterialLot(payload: {
        materialId: number;
        color: number;
        qtyOnHand: number;
        cost: number;
    }): Observable<MaterialLot> {
        return this.http.post<MaterialLot>(this.apiUrl, payload);
    }

    updateMaterialLot(id: number, payload: {
        materialId: number;
        color: number;
        qtyOnHand: number;
        cost: number;
    }): Observable<MaterialLot> {
        return this.http.put<MaterialLot>(`${this.apiUrl}/${id}`, payload);
    }

    deleteMaterialLot(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}