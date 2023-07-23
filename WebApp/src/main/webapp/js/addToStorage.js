import materialUrls from "./urls/materialUrls.js";
import drugUrls from "./urls/drugUrls.js";

export function oninitial() {
    const productList = document.getElementById('product-list');

    const router = new Router();

    router.createFetch(drugUrls.GET_ALL).then(data => {
            console.log("drug", data);
            data.forEach(drug => {
                const option = document.createElement('option');
                option.value = `${drug.id}|drug`;
                option.text = drug.name;
                productList.appendChild(option);
            });
        }
    ).catch(error => console.error(error));

    router.createFetch(materialUrls.GET_ALL).then(data => {
            console.log("material", data);
            data.forEach(material => {
                console.log("each", material)
                const option = document.createElement('option');
                option.value = `${material.id}|material`;
                option.text = material.name;
                productList.appendChild(option);
            });
        }
    ).catch(error => console.error(error));
}