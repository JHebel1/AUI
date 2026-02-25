import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { map, Observable } from 'rxjs'
import { Club } from '../model/club'
import { Clubs } from '../model/clubs'
import { ClubMapperService } from './club-mapper.service'
import { CollectionRepresentation } from '../../core/representation/collection-representation'
import { ClubRepresentation } from '../representation/club-representation'

@Injectable({
  providedIn: 'root'
})
export class ClubService{
  constructor(private http: HttpClient, private mapper: ClubMapperService) {
  }

  getClubs(): Observable<Clubs>{
    return this.http.get<CollectionRepresentation<ClubRepresentation>>('/api/clubs')
      .pipe(map(response => this.mapper.collectionRepresentationToModel(response)))
  }

  getClub(uuid: string): Observable<Club>{
    return this.http.get<Club>(`/api/clubs/${uuid}`).pipe(map(response => this.mapper.representationToModel(response)))
  }

  deleteClub(club: Club): Observable<any>{
    return this.http.delete(`/api/clubs/${club.id}`)
  }

  addClub(data: {name: string; country: string}): Observable<any>{
    return this.http.put('/api/clubs', data);
  }
  putClub(club: Club, clubId: String) : Observable<any>{
    const payload = {
      name: club.name,
      country: club.country
    };
    console.log(clubId)
    return this.http.put(`/api/clubs/${clubId}`, payload);
  }
}
