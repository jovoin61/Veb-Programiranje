<template>
    <div class = "container">
        <h1 class = "text-center">SVI KORISNICI</h1>
        <table class="tabela-prikaz">
            <thead>
                <th>Korisnicko ime</th>
                <th>Ime</th>
                <th>Prezime</th>
            </thead>
            <tbody>
                <tr v-for = "korisnik in korisnici" v-bind:key="korisnik.id">
                    <td>{{korisnik.korisnickoIme}}</td>
                    <td>{{korisnik.ime}}</td>
                    <td>{{korisnik.prezime}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import AdminKorisniciService from '../services/AdminKorisniciService'

    export default {
        name: 'AdminKorisnici',

        data(){
            return{
                korisnici : []
            }
        },

        methods:{
            getKorisnici(){
                AdminKorisniciService.getKorisnici().then((response) =>{
                    this.korisnici = response.data;
                    console.log(response);
                });
            }
        },
        created(){
            this.getKorisnici();
        }
    }
</script>

<style>
    .container {
  margin-top: auto;
  width: 100%;
}

.text-center {
    padding-top: 50px;
    margin-left: auto;
    margin-right: auto;
}

.tabela-prikaz{
    margin-left: auto;
    margin-right: auto;
}
</style>