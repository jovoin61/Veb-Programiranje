<template>
    <form class="form-horizontal" @submit.prevent="handleSubmit">
        <h3>DODAVANJE MENADZERA</h3>

        <div class="form-group">
            <label class="control-label col-sm-2">Korisnicko ime</label>
            <input type="text" v-model="korisnickoIme" placeholder="korisnickoIme"/>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2">Lozinka</label>
            <input type="password" v-model="lozinka" placeholder="lozinka"/>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2">Ime</label>
            <input type="text" v-model="ime" placeholder="ime"/>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2">Prezime</label>
            <input type="text" v-model="prezime" placeholder="prezime"/>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2">Pol</label>
            <input type="text" v-model="pol" placeholder="pol"/>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2">Datum rodjenja</label>
            <input type="date" v-model="datumRodjenja" placeholder="datumRodjenja"/>
        </div>

        <button type="submit">DODAJ MENADZERA</button>
    </form>
</template>

<script>
    import axios from 'axios'

    export default {
        name: 'DodavanjeMenadzera',

        data(){
            return {
                korisnickoIme : "",
                lozinka : "",
                ime : "",
                prezime : "",
                pol : "",
                datumRodjenja : ""
            }
        },

        methods : {
            async handleSubmit(){
                const response = await axios.post('http://localhost:8081/admin/dodavanje/menadzer', {
                    korisnickoIme : this.korisnickoIme,
                    lozinka : this.lozinka,
                    ime : this.ime,
                    prezime : this.prezime,
                    pol : this.pol,
                    datumRodjenja : this.datumRodjenja
                });

                console.log(response);

                this.$router.push('/admin')
            }
        }
    }
</script>