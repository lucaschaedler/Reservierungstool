import { extendObservable } from "mobx";

/**
 * Daten des momentan angemeldeten User speichern
 */

class UserStore {
  constructor() {
    extendObservable(this, {
      loading: true,
      isLoggedIn: false,
      email: "",
      userName: "",
      id: 0,
    });
  }
}

export default new UserStore();
