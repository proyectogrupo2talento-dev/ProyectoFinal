import { Component } from '@angular/core';
import { MaterialListComponent } from './components/material-list/material-list';
import { MaterialLotListComponent } from './components/material-lot-list/material-lot-list';  // ← AGREGAR

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    MaterialListComponent,
    MaterialLotListComponent  // ← AGREGAR AQUÍ
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class AppComponent {
  title = 'artesanias-cuero';
}