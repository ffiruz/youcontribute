import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient:HttpClient) { }

  getGithubAccessToken(code: string):Observable<any>  {

    return this.httpClient.post<any>(`${environment.API_URL}/auth/github/access_token`, {code: code})
  }

}
