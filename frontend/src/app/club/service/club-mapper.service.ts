import { Injectable } from '@angular/core'
import { ClubRepresentation } from '../representation/club-representation'
import { Club } from '../model/club'
import { Clubs } from '../model/clubs'
import { CollectionRepresentation } from '../../core/representation/collection-representation'

@Injectable({
  providedIn: 'root'
})
export class ClubMapperService{
  representationToModel(representation: ClubRepresentation): Club{
    return {
      id: representation.id,
      name: representation.name,
      country: representation.country
    }
  }
  modelToRepresentation(model: Club) : ClubRepresentation{
    return {
      id: model.id,
      name: model.name,
      country: model.country
    }
  }

  collectionRepresentationToModel(representation: CollectionRepresentation<ClubRepresentation>) : Clubs{
    return{
      clubs: representation.items.map(item => this.representationToModel(item))
    }
  }


}
