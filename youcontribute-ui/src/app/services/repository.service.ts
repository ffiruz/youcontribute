import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class RepositoryService {

  constructor(private http: HttpClient) {
   }

   import(organization:String,repository:String): Observable<any> {
     console.log()
    return this.http.post<any>(`${environment.API_URL}/repositories`, {organization:organization,repository:repository})
   }
}
