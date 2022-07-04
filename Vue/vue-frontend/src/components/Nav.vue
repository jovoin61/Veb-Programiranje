<template>
    <div>
        <nav class="navbar navbar-expand navbar-light">
          <div class="container">
            <router-link to="/" class="navbar-brand">Restorani</router-link>
            <div class="collapse navbar-collapse">
              <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                  <router-link to="/prijava" class="nav-link">Prijava</router-link>
                </li>
                <li class="nav-item">
                  <router-link to="/registracija" class="nav-link">Registracija</router-link>
                </li>
                <li class="nav-item">
                  <router-link to="/admin" class="nav-link">Admin</router-link>
                </li>
              </ul>
            </div>
          </div>
        </nav>
    </div>
</template>

<script>
  //import axios from 'axios'

  export default {
      name: 'NavBar'
  }

</script>
