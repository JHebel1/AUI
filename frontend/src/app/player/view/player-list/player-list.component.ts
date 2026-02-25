import { Component, OnInit, ChangeDetectorRef } from '@angular/core'
import { Player } from '../../model/player'
import { Players } from '../../model/players'
import { PlayerService } from '../../service/player.service'
import { CommonModule } from '@angular/common'
import {ClubService} from '../../../club/service/club.service'
import {Clubs} from '../../../club/model/clubs'
@Component({
  selector: 'app-player-list',
  imports: [
    CommonModule
  ],
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css'],
  standalone: true
})

export class PlayerListComponent implements OnInit{

  players: Players = { players: [] };
  clubs: Clubs | undefined;
  constructor(
    private service: PlayerService,
    private clubService: ClubService,
    private cdr: ChangeDetectorRef) {
  }


  ngOnInit(): void{
    this.service.getPlayers().subscribe(players =>
    {
      this.players = players;
      this.cdr.detectChanges();
    });
    this.clubService.getClubs().subscribe(clubs => {
      this.clubs = clubs;
      this.cdr.detectChanges();
    })
  }

  onDelete(player: Player): void{
    this.service.deletePlayer(player).subscribe(() => {
      this.ngOnInit();
      this.cdr.detectChanges();
    });
  }
}
