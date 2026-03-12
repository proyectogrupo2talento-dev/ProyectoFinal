import { Component, OnInit } from "@angular/core";
import { AuthService } from "../../core/services/auth.service";
import { CommonModule } from "@angular/common";
import { Router, RouterModule } from "@angular/router";

@Component({
  selector: "app-home",
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"]
})
export class HomeComponent implements OnInit {

  role: string = "";

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.role = this.authService.getRole();
  }

  irMaterial(): void {
    this.router.navigate(['/material']);
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}