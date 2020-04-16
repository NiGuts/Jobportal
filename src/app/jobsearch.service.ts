import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class JobsearchService {

  constructor(private http: HttpClient) {

   }

   apiUrl = "http://localhost:8080/api";	

   getBerufsfelder(): Observable<string[]> {
        return this.http.get<string[]>(this.apiUrl+"/berufsfeld");
   } 
   getLocationWithZip(): Observable<string[]> {
    return this.http.get<string[]>(this.apiUrl+"/zip");
} 
geEinstiegsarten(): Observable<string[]> {
  return this.http.get<string[]>(this.apiUrl+"/einstiegsart");
} 
getLand(): Observable<string[]> {
  return this.http.get<string[]>(this.apiUrl+"/land");
} 
getRegion(): Observable<string[]> {
  return this.http.get<string[]>(this.apiUrl+"/region");
} 
}
