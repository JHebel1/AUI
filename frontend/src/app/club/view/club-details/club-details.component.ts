import { Component, OnInit } from '@angular/core';
import {ClubService} from '../../service/club.service'
import {Router, ActivatedRoute, RouterLink} from '@angular/router'
import {ChangeDetectorRef} from '@angular/core'
import {forkJoin} from 'rxjs'
import {CommonModule} from '@angular/common'
import {PlayerService} from '../../../player/service/player.service'
import {Players} from '../../../player/model/players'
import {Clubs} from '../../model/clubs';
import {Club} from '../../model/club';
import {Player} from '../../../player/model/player';
@Component({
  selector: 'app-club-details',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './club-details.component.html',
  styleUrl: './club-details.component.css',
})
export class ClubDetailsComponent implements OnInit{
  players: Players | undefined;
  club : Club | undefined;
  constructor(
    private clubService: ClubService,
    private playerService: PlayerService,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {
  }
  ngOnInit(){
    this.route.params.subscribe(params => {
      forkJoin({
        club : this.clubService.getClub(params['uuid']),
        players : this.playerService.getPlayersByClubId(params['uuid'])
      }).subscribe({
        next: ({club, players}) => {
          this.club = club;
          this.players = players;
          this.cdr.detectChanges();
        }
      })
    })
  }
  onDelete(player: Player): void{
    this.playerService.deletePlayer(player).subscribe(() => {
      this.ngOnInit();
      this.cdr.detectChanges();
    });
  }
}
