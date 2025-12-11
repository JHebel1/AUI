import { Injectable } from '@angular/core'
import { PlayerRepresentation } from '../representation/player-representation'
import { Player } from '../model/player'
import { Players } from '../model/players'
import { CollectionRepresentation } from '../../core/representation/collection-representation'
import { ClubMapperService } from '../../club/service/club-mapper.service'

@Injectable({
  providedIn: 'root'
})
export class PlayerMapperService{
  constructor(private clubMapper: ClubMapperService) {
  }
  representationToModel(representation: PlayerRepresentation): Player{
    return {
      id: representation.id,
      name: representation.name,
      surname: representation.surname,
      age: representation.age,
      club: representation.club
    }
  }
  modelToRepresentation(model: Player) : PlayerRepresentation{
    return {
      id: model.id,
      name: model.name,
      surname: model.surname,
      age: model.age,
      club: model.club
    }
  }

  collectionRepresentationToModel(representation: CollectionRepresentation<PlayerRepresentation>) : Players{
    return{
      players: representation.items.map(item => this.representationToModel(item))
    }
  }
}
