import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { map, Observable } from 'rxjs'
import { Player } from '../model/player'
import { Players } from '../model/players'
import { PlayerMapperService } from './player-mapper.service'
import { CollectionRepresentation } from '../../core/representation/collection-representation'
import { PlayerRepresentation } from '../representation/player-representation'

@Injectable({
  providedIn: 'root'
})
export class PlayerService{
  constructor(private http: HttpClient, private mapper: PlayerMapperService) {
  }

  getPlayers(): Observable<Players>{
    return this.http.get<CollectionRepresentation<PlayerRepresentation>>('/api/players')
      .pipe(map(response => this.mapper.collectionRepresentationToModel(response)))
  }

  getPlayer(uuid: String): Observable<Player>{
    return this.http.get<PlayerRepresentation>(`/api/players/${uuid}`)
      .pipe(map(response => this.mapper.representationToModel(response)))
  }

  getPlayersByClubId(uuid: String){
    return this.http.get<CollectionRepresentation<PlayerRepresentation>>(`/api/clubs/${uuid}/players`)
      .pipe(map(response => this.mapper.collectionRepresentationToModel(response)))
  }

  addClubPlayer(data: {name: string; surname: string; age:string}, clubID: string): Observable<any>{
    return this.http.put(`/api/clubs/${clubID}/players`, data);
  }

  putPlayer(player: Player, playerId: String): Observable<any>{
    return this.http.put(`/api/players/${playerId}`, player);
  }

  deletePlayer(player: Player): Observable<any>{
    return this.http.delete(`/api/players/${player.id}`)
  }
}
