import { Component, OnInit } from '@angular/core';
import { ClubService } from '../../../club/service/club.service'
import { PlayerService } from '../../service/player.service'
import {Router, ActivatedRoute} from '@angular/router'
import {FormsModule} from '@angular/forms'
import {CommonModule} from '@angular/common'
import {Player} from '../../model/player'
import {Club} from '../../../club/model/club'
import {ChangeDetectorRef} from '@angular/core'
@Component({
  selector: 'app-player-club-details.component',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './player-club-details.component.html',
  styleUrl: './player-club-details.component.css',
})
export class PlayerClubDetailsComponent implements OnInit{

  player: Player | undefined;
  club: Club | undefined;
  constructor(
    private playerService: PlayerService,
    private clubService: ClubService,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef
  ) {
  }

  ngOnInit(){
    this.route.params.subscribe(params => {
      const id = params['playerId'];
      const clubId = params['clubId']
      this.playerService.getPlayer(id).subscribe(player => {
        this.player = player;
        this.cdr.detectChanges();
      });
      this.clubService.getClub(clubId).subscribe(club => {
        this.club = club;
        this.cdr.detectChanges();
      })
    });
  }

}
