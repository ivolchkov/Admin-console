import {AddressDTO} from "./AddressDTO";

export class UserDTO {
  id: number;
  name: string;
  surname: string;
  login: string;
  password: string;
  about: string;
  dateOfBirth: Date;
  address: AddressDTO;
}
