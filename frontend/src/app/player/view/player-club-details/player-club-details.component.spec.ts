import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayerClubDetailsComponent } from './player-club-details.component';

describe('PlayerClubDetailsComponent', () => {
  let component: PlayerClubDetailsComponent;
  let fixture: ComponentFixture<PlayerClubDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PlayerClubDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlayerClubDetailsComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
