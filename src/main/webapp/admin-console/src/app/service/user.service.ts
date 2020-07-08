import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url:string = "http://localhost:8080/users";

  constructor(private http:HttpClient) { }

  public register(user) : Observable<any>{
    return this.http.post(this.url, user, {responseType:'text' as 'json'});
  }

  public findAll() {
    return this.http.get(this.url);
  }

  public update(user) {
    return this.http.put(`${this.url}/${user.id}`, user, {responseType:'text' as 'json'});
  }

  public delete(id) {
    this.http.delete(`${this.url}/${id}`);
  }
}
