import { Component, OnInit } from '@angular/core';
import {UserDTO} from "../../dto/UserDTO";
import {UserService} from "../../service/user.service";
import {AddressDTO} from "../../dto/AddressDTO";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user:UserDTO = new UserDTO();

  constructor(private userService:UserService) { }

  ngOnInit(): void {
    this.user.address = new AddressDTO();
  }

  public registerUser() {
    this.userService.register(this.user).subscribe(data => console.log(data), error => console.log(error));
    this.user = new UserDTO();
    this.user.address = new AddressDTO();
  }

}
