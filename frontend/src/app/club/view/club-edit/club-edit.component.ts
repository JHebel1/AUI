import { Component, OnInit } from '@angular/core';
import { Club } from '../../model/club'
import {ClubService} from '../../service/club.service';
import {Router, ActivatedRoute} from '@angular/router'
import {CommonModule} from '@angular/common'
import {FormsModule} from '@angular/forms'
import {ChangeDetectorRef} from '@angular/core'
@Component({
  selector: 'app-club-edit',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './club-edit.component.html',
  styleUrl: './club-edit.component.css',
})
export class ClubEditComponent implements OnInit{
  constructor(
    private clubService: ClubService,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {
  }
  uuid : string | undefined;

  club : Club | undefined;

  original : Club | undefined;

  ngOnInit(){
    this.route.params.subscribe(params => {
      const id = params['uuid'];

      this.clubService.getClub(id).subscribe(club => {
        this.club = club;
        this.uuid = id;
        this.original = structuredClone(club);
        this.cdr.detectChanges();
        console.log(this.club.name)
      });
    });

  }

  onSubmit(): void{
    this.clubService.putClub(this.club!, this.uuid!)
      .subscribe(() => this.router.navigate(['/clubs']));
  }
}
