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
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { IssueListComponent } from './issues/issue-list/issue-list.component';
import { AccordionModule } from 'ngx-bootstrap/accordion';
import { AcceptComponent } from './challenges/accept/accept.component';
import { RejectComponent } from './challenges/reject/reject.component';
import { ChallengeListComponent } from './challenges/challenge-list/challenge-list.component';
import { Challenge } from './_models/challenge';
import { StatusComponent } from './badges/status/status.component';
import { SigninComponent } from './auth/signin/signin.component';
import { CallbackComponent } from './auth/callback/callback.component';
import { GithubCallbackComponent } from './auth/github-callback/github-callback.component';
import { PermissionService } from './_services/permission.service';



const routes: Routes = [
  { path: 'home', component: HomeComponent,canActivate: [PermissionService]},
  { path: 'import', component: ImportComponent,canActivate: [PermissionService] },
  { path: 'issues/:id', component: IssueListComponent,canActivate: [PermissionService] },
  { path: 'challenges/:id/accept', component: AcceptComponent,canActivate: [PermissionService] },
  { path: 'challenges/:id/reject', component: RejectComponent,canActivate: [PermissionService] },
  { path: 'challenges', component: ChallengeListComponent,canActivate: [PermissionService] },
  { path: 'signin', component: SigninComponent },
  { path: 'auth/github/callback', component: GithubCallbackComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ImportComponent,
    IssueListComponent,
    AcceptComponent,
    RejectComponent,
    ChallengeListComponent,
    StatusComponent,
    SigninComponent,
    CallbackComponent,
    GithubCallbackComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FontAwesomeModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(),  // ToastrModule added
     AccordionModule.forRoot(), 
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
