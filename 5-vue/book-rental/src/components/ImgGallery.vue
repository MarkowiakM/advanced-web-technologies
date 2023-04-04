<script lang="ts">
type Image = {
  cols: number;
  path: string;
  children?: Image[];
};

export default {
  data: () => ({
    imageLayout: [
      {
        cols: 4,
        path: 'https://www.incimages.com/uploaded_files/image/1920x1080/getty_506755198_362244.jpg'
      },
      {
        cols: 8,
        path: 'https://www.shutterstock.com/image-vector/school-backgrounds-stack-books-stationery-600w-1962774379.jpg'
      },
      {
        cols: 8,
        children: [
          {
            cols: 12,
            path: 'https://www.shutterstock.com/image-vector/dental-clinic-web-banner-design-600w-2197857815.jpg'
          },
          {
            cols: 12,
            path: 'https://www.shutterstock.com/image-vector/new-products-introduction-web-banner-600w-2108915585.jpg'
          }
        ]
      },
      {
        cols: 6,
        path: 'https://www.shutterstock.com/image-vector/grocery-sale-vertical-banners-vector-600w-29170486.jpg'
      },
      {
        cols: 3,
        path: 'https://www.shutterstock.com/image-vector/colorful-geometric-background-modern-abstract-600w-1640799766.jpg'
      },
      {
        cols: 9,
        path: 'https://www.shutterstock.com/image-vector/sketch-natural-cheese-brochures-inscriptions-600w-629332448.jpg'
      },
      {
        cols: 4,
        path: 'https://www.shutterstock.com/image-vector/grocery-sale-vertical-banners-vector-600w-29170486.jpg'
      },
      {
        cols: 8,
        path: 'https://www.shutterstock.com/image-vector/birthday-mega-collection-posters-cake-600w-2162482117.jpg'
      }
    ] as Image[]
  })
};
</script>

<template>
  <v-row class="wrapper">
    <template v-for="(image, imgIdx) in imageLayout" :key="imgIdx">
      <v-col :cols="image.cols">
        <v-img :src="image.path" cover height="100%"></v-img>
      </v-col>

      <v-col v-if="image.children" cols="6" class="d-flex flex-column">
        <v-row>
          <v-col
            v-for="(children, childIdx) in image.children"
            :key="childIdx"
            :cols="children.cols"
          >
            <v-img :src="children.path" cover height="100%"></v-img>
          </v-col>
        </v-row>
      </v-col>
    </template>
  </v-row>
</template>

<style scoped>
.wrapper {
  width: 90vw;
  margin: 30px auto;
}
</style>
