import { Component, OnInit, ChangeDetectorRef } from '@angular/core'
import { Club } from '../../model/club'
import { Clubs } from '../../model/clubs'
import { ClubService } from '../../service/club.service'
import { CommonModule } from '@angular/common'
import { RouterLink } from '@angular/router'

@Component({
  selector: 'app-club-list',
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './club-list.component.html',
  styleUrls: ['./club-list.component.css'],
  standalone: true
})

export class ClubListComponent implements OnInit{

  clubs: Clubs = { clubs: [] };
  constructor(
    private service: ClubService,
    private cdr: ChangeDetectorRef) {
  }


  ngOnInit(): void{
    this.service.getClubs().subscribe(clubs =>
    {
      this.clubs = clubs;
      this.cdr.detectChanges();
    });
  }

  onDelete(club: Club): void{
    this.service.deleteClub(club).subscribe(() => {
      this.ngOnInit();
      this.cdr.detectChanges();
    });
  }
}
