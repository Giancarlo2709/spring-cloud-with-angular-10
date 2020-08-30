import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Generic } from '../models/generic';
// import { map } from 'rxjs/operators';

export abstract class CommonService<E extends Generic> {

  protected baseEndpoint: string ;

  protected headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(protected http: HttpClient) { }

  public findAll(): Observable<E[]> {
    // se puede de las 2 formas
    return this.http.get<E[]>(this.baseEndpoint);
    /* return this.http.get(this.baseEndpoint).pipe(
      map(students => students as Student[])
    ); */
  }

  public findAllPageable(page: string, size: string): Observable<any> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size);
    return this.http.get<any>(`${this.baseEndpoint}/page`, { params: params });
  }

  public findById(id: number): Observable<E> {
    return this.http.get<E>(`${this.baseEndpoint}/${id}`);
  }

  public create(e: E): Observable<E> {
    return this.http.post<E>(this.baseEndpoint, e,
      { headers: this.headers });
  }

  public update(e: E): Observable<E> {
    return this.http.put<E>(`${this.baseEndpoint}/${e.id}`, e,
      { headers: this.headers });
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseEndpoint}/${id}`);
  }

}
