<template @submit.prevent="handleSubmit">
    <form class="form-horizontal">
        <h3>PRIJAVA</h3>

        <div class="form-group">
            <label class="control-label col-sm-2">Korisnicko ime</label>
            <input type="text" v-model="korisnickoIme" placeholder="korisnickoIme"/>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2">Lozinka</label>
            <input type="password" v-model="lozinka" placeholder="lozinka"/>
        </div>

        <button type="submit">Prijavi se</button>
    </form>
</template>


<script>
    import axios from 'axios'

    export default {
        name: 'PrijvaKorisnika',

        data() {
            return {
                korisnickoIme: '',
                lozinka: ''
            }
        },
       
        methods: {
            async handleSubmit(){
                const response = await axios.post('/prijava', {
                    korisnickoIme : this.korisnickoIme,
                    lozinka: this.lozinka
                });

                console.log(response);
            }
        }
    }
</script>

<style>
    
</style>
