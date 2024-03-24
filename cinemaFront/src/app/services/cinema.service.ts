import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CinemaService {
  private host:string="http://localhost:8080"

  constructor(private http: HttpClient) { }

  public getVilles(){
    return this.http.get(this.host+"/villes");
  }

  public getCinemas(v) {
    return this.http.get(v._links.cinemas.href)
  }

  getSalles(c) {
    return this.http.get(c._links.salles.href)
  }
}
