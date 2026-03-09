import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialLot } from '../../models/material-lot';
import { MaterialLotService } from '../../services/material-lot.service';

@Component({
  selector: 'app-material-lot-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './material-lot-list.html',
  styleUrl: './material-lot-list.css'
})
export class MaterialLotListComponent implements OnInit {
  lots: MaterialLot[] = [];
  loading = false;

  constructor(private materialLotService: MaterialLotService) { }

  ngOnInit(): void {
    this.cargarLotes();
  }

  cargarLotes(): void {
    this.loading = true;
    this.materialLotService.getLots().subscribe({
      next: (data) => {
        this.lots = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error al cargar lotes:', err);
        this.loading = false;
      }
    });
  }
}