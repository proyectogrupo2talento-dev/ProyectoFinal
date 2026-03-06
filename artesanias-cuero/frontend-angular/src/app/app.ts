import { Component } from '@angular/core';
import { MaterialListComponent } from './components/material-list/material-list';  // ← Ruta exacta

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    MaterialListComponent  // ← Solo la clase, sin comillas
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class AppComponent {
  title = 'artesanias-cuero';
}