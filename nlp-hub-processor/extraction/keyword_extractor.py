from sys import prefix
import nlpcloud
from fastapi import APIRouter,HTTPException
from fastapi.encoders import jsonable_encoder
from fastapi.responses import JSONResponse 

router = APIRouter(prefix ='/keyword')

keyword_client = nlpcloud.Client("finetuned-gpt-neox-20b", "a88b74e04fc38f34c02c34664ebe2f41718bfef2", gpu=True, lang="en")

@router.post("/extract/{query}")
async def get_result(query: str):
    data = keyword_client.kw_kp_extraction(query)
    print(data)
    json_data = jsonable_encoder(data)
    return JSONResponse(content=json_data)

