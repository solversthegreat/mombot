from ast import keyword
from typing import Union
import app
from fastapi import FastAPI
from fastapi.encoders import jsonable_encoder
from fastapi.responses import JSONResponse 
from extraction import summary,keyword_extractor

app = FastAPI()
app.include_router(summary.router)
app.include_router(keyword_extractor.router)


@app.get("/")
def read_root():
    return {"MOMBOT-NLP": "SolversTheGreat"}

