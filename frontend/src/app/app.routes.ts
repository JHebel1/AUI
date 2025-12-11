import { Routes } from '@angular/router';
import { provideRouter } from '@angular/router'

export const routes: Routes = [
  {
    path: '',
    loadComponent: ()=>
      import('./ui/component/main/main.component')
        .then(m => m.MainComponent)
  },
  {
    path: 'clubs',
    loadComponent: () =>
      import('./club/view/club-list/club-list.component')
        .then(m=>m.ClubListComponent)
  },
  {
    path: 'clubs/add',
    loadComponent: () =>
      import('./club/view/club-add/club-add.component')
        .then(c=>c.ClubAddComponent)
  },
  {
    path: 'clubs/:uuid/edit',
    loadComponent: () =>
      import('./club/view/club-edit/club-edit.component')
        .then(c=>c.ClubEditComponent)
  },
  {
    path: 'clubs/:uuid',
    loadComponent: () =>
      import('./club/view/club-details/club-details.component')
        .then(c=>c.ClubDetailsComponent)
  },
  {
    path: 'clubs/:uuid/addplayer',
    loadComponent: () =>
      import('./player/view/add-player-club/add-player-club.component')
        .then(p=>p.AddPlayerClubComponent)
  },
  {
    path: 'clubs/:clubId/editplayer/:playerId',
    loadComponent: () =>
      import('./player/view/edit-player-club/edit-player-club.component')
        .then(p=>p.EditPlayerClubComponent)

  },
  {
    path: 'clubs/:clubId/players/:playerId',
    loadComponent: () =>
      import('./player/view/player-club-details/player-club-details.component')
        .then(p=>p.PlayerClubDetailsComponent)
  },

  {
    path: 'players',
    loadComponent: () =>
      import('./player/view/player-list/player-list.component')
        .then(p=>p.PlayerListComponent)
  }
];
