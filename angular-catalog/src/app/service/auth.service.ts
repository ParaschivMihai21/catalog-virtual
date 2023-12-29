import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse} from "@angular/common/http";
import {catchError, map, Observable, throwError} from "rxjs";
import {UserDto} from "../class/user-dto";
import {JwtHelperService} from "@auth0/angular-jwt";
import {CredentialsDto} from "../class/credentials-dto";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl = "http://localhost:8080"
  private _httpOptions = {
    headers: new HttpHeaders({'Content-type': 'application/json'})


  };

  constructor(private httpClient: HttpClient, private jwtHelper: JwtHelperService) {
  }

  isLoggedIn(): boolean {
    return !this.jwtHelper.isTokenExpired(localStorage.getItem("token"))
  }

  logout(): void {
    localStorage.removeItem("token");
  }

  getToken(): string | null {
    if (this.isLoggedIn()) {
      return localStorage.getItem("token");
    }
    return null;
  }

  private addAuthorizationHeader(): void {
    const token = localStorage.getItem('token');
    if (token) {
      this._httpOptions.headers = this._httpOptions.headers.set('Authorization', `Bearer ${token}`);
    }
  }

  get httpOptions(): { headers: HttpHeaders } {
    this.addAuthorizationHeader();
    return this._httpOptions;
  }



    signIn(credentialsDto:CredentialsDto):Observable<boolean>{
        return this.httpClient.post(`${this.baseUrl}/login`,credentialsDto,{observe:'response' , responseType:'text'})
            .pipe(
                map((response:HttpResponse<any>)=>{
                    const token:string=response.body;
                    console.log(response);
                    localStorage.setItem('token',token);
                    return true;
                }),
                catchError((error:HttpErrorResponse)=>{
                  console.error('login error',error)
                    return throwError(error);
                })
            );
    }



  registerUser(user: UserDto | undefined): Observable<String> {
    return this.httpClient.post(`${this.baseUrl}/register`, user, {responseType: `text`})
  }

}
