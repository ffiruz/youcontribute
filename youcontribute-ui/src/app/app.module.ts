import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ImportComponent } from './import/import.component';
import {Routes} from "@angular/router";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'import', component: ImportComponent },
  { path: '',redirectTo:'home',pathMatch:'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ImportComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FontAwesomeModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }