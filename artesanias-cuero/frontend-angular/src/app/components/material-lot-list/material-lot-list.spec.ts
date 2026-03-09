import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialLotList } from './material-lot-list';

describe('MaterialLotList', () => {
  let component: MaterialLotList;
  let fixture: ComponentFixture<MaterialLotList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MaterialLotList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MaterialLotList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
