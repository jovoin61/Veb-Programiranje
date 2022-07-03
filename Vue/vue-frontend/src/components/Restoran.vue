<template>
    <div class = "container">
        <h1 class = "text-center">RESTORANI</h1>
        <table class="tabela-prikaz">
            <thead>
                <th>Naziv</th>
                <th>Tip</th>
                <th>Adresa</th>
            </thead>
            <tbody>
                <tr v-for = "restoran in restorani" v-bind:key="restoran.id" :href="`/restoran/${restoran.id}`">
                    <a href=""><td>{{restoran.naziv}}</td></a>
                    <td>{{restoran.tip}}</td>
                    <td>{{restoran.lokacija.adresa}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import RestoranService from '@/services/RestoranService'

    export default {
        name: 'Restorani-ispis',
        data(){
            return{
                restorani : []
            }
        },
        methods:{
            getRestorani(){
                RestoranService.getRestorani().then((response) =>{
                    this.restorani = response.data;
                });
            }
        },
        created(){
            this.getRestorani()
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