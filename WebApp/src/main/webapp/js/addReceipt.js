import storageUrls from "./urls/storageUrls.js";
import materialUrls from "./urls/materialUrls.js";
import drugUrls from "./urls/drugUrls.js";
import {createGenericTable} from "./table/table.js";
import pharmacyUrls from "./urls/pharmacyUrls.js";

export async function sendPharmacyData() {


    const router = new Router()
    let materialData, drugData;
    const material = Promise.resolve(router.createFetch(materialUrls.GET_ALL));

    materialData = await material;

    console.log("m", materialData);
    const drug = Promise.resolve(router.createFetch(drugUrls.GET_ALL));

    drugData = await drug;
    return {
        material: materialData,
        drug: drugData
    };
}


export async function oninitial() {
    const drugList = document.getElementById('Drug-list');
    const materialList = document.getElementById('Material-list');

    const router = new Router();
    router.createFetch(drugUrls.GET_ALL).then(data => {
            console.log("drug", data);
            data.forEach(drug => {
                const option = document.createElement('option');
                option.value = `${drug.id}|${drug.price}`;
                option.text = drug.name;
                drugList.appendChild(option);
            });
        }
    ).catch(error => console.error(error));

    router.createFetch(materialUrls.GET_ALL).then(data => {
            console.log("material", data);
            data.forEach(material => {
                console.log("each", material)
                const option = document.createElement('option');
                option.value = `${material.id}|${material.price}`;
                option.text = material.name;
                materialList.appendChild(option);
            });
        }
    ).catch(error => console.error(error));
}



