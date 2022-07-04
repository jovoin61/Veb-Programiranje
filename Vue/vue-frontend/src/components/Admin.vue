<template>
         <div class = "container">
        <h1 class = "text-center">ADMIN MENI</h1>
        <table class="tabela-prikaz">
            <tbody>
                <ul>
                    <li>
                        <router-link to="/admin/korisnici">SVI KORISNICI</router-link>
                    </li>

                    <li>
                        <router-link to="/admin">DODAVANJE MENADZERA</router-link>
                    </li>

                    <li>
                        <router-link to="/admin">DODAVANJE DOSTAVLJACA</router-link>
                    </li>

                    <li>
                        <router-link to="/admin">DODAVANJE RESTORANA</router-link>
                    </li>
                </ul>
            </tbody>
        </table>
    </div>
</template>

<script>

    export default {
        name: 'AdminPocetna',
        props: {
            msg: String
        }
    }
</script>

<style>
</style>