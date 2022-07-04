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
                    <td>{{}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import AdminKorisniciService from '@/services/AdminKorisniciService';

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
            this.getKorisnici()
        }
    }
</script>

<style>
</style>