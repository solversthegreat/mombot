from sys import prefix
import nlpcloud
from fastapi import APIRouter,HTTPException
from utility import list_processor

router = APIRouter(prefix ='/summary')

summary_client = nlpcloud.Client("bart-large-cnn", "a88b74e04fc38f34c02c34664ebe2f41718bfef2", gpu=False, lang="en")
dialogue_client = nlpcloud.Client("bart-large-samsum", "a88b74e04fc38f34c02c34664ebe2f41718bfef2", gpu=False, lang="en")


@router.post("/text/{query}")
async def get_result(query: str):
    data = summary_client.summarization(query)
    output = list_processor.convert_to_list(data.get("summary_text"))
    return output

@router.post("/dialogue/{query}")
async def get_result(query: str):
    data = dialogue_client.summarization(query)
    output = list_processor.convert_to_list(data.get("summary_text"))
    return output

