<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: 'Predict' }" />
        <VerticalNavLink
            :item="{
                title: '사망자 수 예측',
                to: '/deathPredictions',
            }"
        />
    </ul>
</template>
