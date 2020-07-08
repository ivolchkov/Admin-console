import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationComponent} from "./component/registration/registration.component";


const routes: Routes = [
  { path: '', redirectTo: 'view-user', pathMatch: 'full' },
  // { path: 'view-user', component: StudentListComponent },
  { path: 'add-user', component: RegistrationComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
