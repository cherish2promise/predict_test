<template>
    <div>
        <Date
            label="Date"
            v-model="value.date"
            :editMode="editMode"
        />
        <String
            label="Region"
            v-model="value.region"
            :editMode="editMode"
        />
        <Number
            label="PredictedDeaths"
            v-model="value.predictedDeaths"
            :editMode="editMode"
        />
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'DeathPrediction',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "deathPredictions",
        value: {
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
