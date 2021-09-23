import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { ConsultaEmpleadosComponent } from './components/consulta-empleados/consulta-empleados.component';
import { RegistrarEmpleadosComponent } from './components/registrar-empleados/registrar-empleados.component';

const routes: Routes = [
  {
    path: '', component: AppComponent,
    children: [
      { path: '', component: ConsultaEmpleadosComponent, },
      { path: 'registrar', component: RegistrarEmpleadosComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
