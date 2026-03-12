import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { MaterialComponent } from './pages/materials/material.component';
import { MaterialLotComponent } from './pages/material-lots/material-lot.component';
import { OrderComponent } from './pages/orders/order.component';
import { UserComponent } from './pages/users/user.component';


export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent },
    { path: 'material', component: MaterialComponent },
    { path: 'material-lot', component: MaterialLotComponent },
    { path: 'orders', component: OrderComponent },
    { path: 'users', component: UserComponent },
];


