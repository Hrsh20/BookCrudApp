import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMulitpleBookComponent } from './add-mulitple-book.component';

describe('AddMulitpleBookComponent', () => {
  let component: AddMulitpleBookComponent;
  let fixture: ComponentFixture<AddMulitpleBookComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddMulitpleBookComponent]
    });
    fixture = TestBed.createComponent(AddMulitpleBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
