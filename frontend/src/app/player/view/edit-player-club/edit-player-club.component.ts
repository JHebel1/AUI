import { Component, OnInit } from '@angular/core';
import { ClubService } from '../../../club/service/club.service'
import { PlayerService } from '../../service/player.service'
import {Router, ActivatedRoute} from '@angular/router'
import {FormsModule} from '@angular/forms'
import {CommonModule} from '@angular/common'
import {forkJoin} from 'rxjs'
import {Player} from '../../model/player'
import {Clubs} from '../../../club/model/clubs'
import {ChangeDetectorRef} from '@angular/core'

@Component({
  selector: 'app-edit-player-club',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './edit-player-club.component.html',
  styleUrl: './edit-player-club.component.css',
})
export class EditPlayerClubComponent implements OnInit{

  playerId: string | undefined;
  player: Player | undefined;
  original: Player | undefined;
  clubs: Clubs | undefined;
  constructor(
    private playerService: PlayerService,
    private clubService: ClubService,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {
  }

  ngOnInit(){
    this.route.params.subscribe(params => {
      forkJoin({
        clubs: this.clubService.getClubs(),
        player: this.playerService.getPlayer(params['playerId']),
        clubId: params['clubId']
      }).subscribe({
        next: ({clubs, player}) => {
          this.clubs = clubs;
          this.player = player;
          this.playerId = player.id;
          this.original = {...this.player};
          this.cdr.detectChanges();
          console.log(player)
        }
      })
    })
  }

  onSubmit(): void{
    this.playerService.putPlayer(this.player!, this.playerId!)
      .subscribe(() => this.router.navigate([`/clubs/${this.player?.club}`]))
  }
}
